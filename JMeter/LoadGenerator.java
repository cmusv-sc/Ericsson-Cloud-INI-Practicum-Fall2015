import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.RouteMatcher;
import org.vertx.java.platform.Verticle;

public class LoadGenerator extends Verticle {
	private Map<String, String> info = new HashMap<String, String>();
	private Process process = null;
	private long testId = 0;
	private String passwd = "";
	private List<String> testLogs = new ArrayList<String>();

	private boolean isAlive(Process process) {
		try {
			process.exitValue();
		} catch (IllegalThreadStateException e) {
			return true;
		}
		return false;
	}

	private boolean isTesting() {
		return process != null && isAlive(process);
	}

	private synchronized boolean startConstant(String dns) {
		try {
			if (!isTesting()) {
				this.testId = System.currentTimeMillis();
				process = Runtime.getRuntime()
						.exec(String.format("./test.constant.sh %s test.%d.html 1 %s", dns, testId, this.passwd));
				this.testLogs.add(String.format("test.%d.html", testId));
				return true;
			}
		} catch (IOException e) {
			container.logger().error(e);
		}
		return false;
	}

	private synchronized boolean startStep(String dns) {
		try {
			if (!isTesting()) {
				this.testId = System.currentTimeMillis();
				process = Runtime.getRuntime()
						.exec(String.format("./test.step.sh %s test.%d.html 1 %s", dns, testId, this.passwd));
				this.testLogs.add(String.format("test.%d.html", testId));
				return true;
			}
		} catch (IOException e) {
			container.logger().error(e);
		}
		return false;
	}

	private synchronized boolean startCurve(String dns) {
		try {
			if (!isTesting()) {
				this.testId = System.currentTimeMillis();
				process = Runtime.getRuntime()
						.exec(String.format("./test.curve.sh %s test.%d.html 1 %s", dns, testId, this.passwd));
				this.testLogs.add(String.format("test.%d.html", testId));
				return true;
			}
		} catch (IOException e) {
			container.logger().error(e);
		}
		return false;
	}

	private String prettify(String dns) {

		if (dns != null) {
			dns = dns.toLowerCase().replace("http://", "").replace("https://", "").replace("/", "").replace(" ", "");
		}
		return dns;
	}

	@Override
	public void start() {
		final HttpServer server = vertx.createHttpServer();
		server.setAcceptBacklog(32767);
		server.setUsePooledBuffers(true);
		server.setReceiveBufferSize(4 * 1024);

		final RouteMatcher routeMatcher = new RouteMatcher();

		routeMatcher.all("/constant", new Handler<HttpServerRequest>() {
			public void handle(final HttpServerRequest req) {
				if (isTesting()) {
					sendErr(req, String.format("<a href='/log?name=test.%d.html'>Test</a> running.", testId));
					return;
				}
				if (!req.params().contains("dns")) {
					send(req,
							"<form>Please enter DNS of your API Gateway: <input type='text' name='dns'/><br><input type='submit'/></form>");
					return;
				}
				String dns = prettify(req.params().get("dns"));
				if (!startConstant(dns)) {
					sendErr(req, "Something wrong. Please try again.");
				} else {
					send(req, String.format("<a href='/log?name=test.%d.html'>Test</a> launched.", testId));
				}
			}
		});

		routeMatcher.all("/step", new Handler<HttpServerRequest>() {
			public void handle(final HttpServerRequest req) {
				if (isTesting()) {
					sendErr(req, String.format("<a href='/log?name=test.%d.html'>Test</a> running.", testId));
					return;
				}
				if (!req.params().contains("dns")) {
					send(req,
							"<form>Please enter DNS of your API Gateway: <input type='text' name='dns'/><br><input type='submit'/></form>");
					return;
				}
				String dns = prettify(req.params().get("dns"));
				if (!startStep(dns)) {
					sendErr(req, "Something wrong. Please try again.");
				} else {
					send(req, String.format("<a href='/log?name=test.%d.html'>Test</a> launched.", testId));
				}
			}
		});

		routeMatcher.all("/bell", new Handler<HttpServerRequest>() {
			public void handle(final HttpServerRequest req) {
				if (isTesting()) {
					sendErr(req, String.format("<a href='/log?name=test.%d.html'>Test</a> running.", testId));
					return;
				}
				if (!req.params().contains("dns")) {
					send(req,
							"<form>Please enter DNS of your API Gateway: <input type='text' name='dns'/><br><input type='submit'/></form>");
					return;
				}
				String dns = prettify(req.params().get("dns"));
				if (!startCurve(dns)) {
					sendErr(req, "Something wrong. Please try again.");
				} else {
					send(req, String.format("<a href='/log?name=test.%d.html'>Test</a> launched.", testId));
				}
			}
		});

		routeMatcher.all("/info/:param", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest req) {
				String response = info.get(req.params().get("param"));
				req.response().putHeader("Content-Length", String.valueOf(response.length()));
				req.response().end(response);
				req.response().close();
			}
		});

		routeMatcher.all("/info", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest req) {
				StringBuffer buffer = new StringBuffer();
				for (Map.Entry<String, String> entry : info.entrySet()) {
					buffer.append(entry.getKey());
					buffer.append('=');
					buffer.append(entry.getValue());
					buffer.append('\n');
				}
				send(req, buffer.toString());
			}
		});

		routeMatcher.all("/log", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest req) {
				if (req.params().contains("name")) {
					String name = req.params().get("name");
					if (name.startsWith("test") && name.endsWith("html")) {
						try {
							File file = new File("/home/ubuntu/" + name);
							Scanner scanner = new Scanner(file);
							String content = scanner.useDelimiter("\\Z").next();
							scanner.close();
							send(req, content);
						} catch (Exception ex) {
							System.out.println("Something went wrong");
							req.response().end("Something went wrong");
						}
					} else {

						req.response().end();
					}
				} else {
					StringBuffer response = new StringBuffer();
					if (!testLogs.isEmpty())
						response.append("Test Logs<br>");
					for (String log : testLogs)
						response.append(String.format("<a href='/log?name=%s'>%s</a><br>", log, log));
					send(req, response.toString());
				}
			}
		});

		routeMatcher.all("/test/kill", new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest req) {
				if (process != null) {
					process.destroy();
					System.out.println("Test killed");
				}
				send(req, String.format("<a href='/log?name=test.%d.html'>Test</a> killed", testId));
			}
		});

		routeMatcher.noMatch(new Handler<HttpServerRequest>() {
			@Override
			public void handle(final HttpServerRequest req) {
				String response = String
						.format("<h3>Welcome to Media Application Load Generator & Test Center</h3><br>");
				if (isTesting())
					response += String.format(
							"<a href='/log?name=test.%d.html'>Test</a> is already running. <a href='/test/kill'>Want to kill</a> it?<br>",
							testId);
				response += "<h4>GET-Movie-Details Tests</h4><br>";
				response += "1. <a href='/constant'>Constant Load (5 minutes)</a><br>";
				response += "2. <a href='/step'>Step Function Load (20 minutes)</a><br>";
				response += "3. <a href='/bell'>Bell Curve (20 minutes)</a><br>";
				response += "<br>";
				response += "<a href='/log'>View Previous Test logs</a><br>";
				send(req, response);
			}
		});

		server.requestHandler(routeMatcher);
		server.listen(80);
	}

	private void sendErr(final HttpServerRequest req, String content) {
		req.response().setStatusCode(400);
		send(req, content);
	}

	private void send(final HttpServerRequest req, String content) {
		req.response().putHeader("Content-Type", "text/html");
		String response = "<!DOCTYPE html><html><head><title>Media Application Load Generator</title></head><body>"
				+ content + "</body></html>";
		req.response().putHeader("Content-Length", String.valueOf(response.length()));
		req.response().end(response);
		req.response().close();
	}

}

