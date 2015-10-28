import urllib2
import json

MOVIE_FILE = "/home/abhishek/code/Practicum/omdbFull0815/omdbFull.txt"
MOVIE_SERVICE_URL = "http://localhost:5050/movie/"

def load_movies():
    fp = open(MOVIE_FILE)
    required_keys = {'id', 'imdbid', 'title', 'year', 'runtime', 'genre', 'director', 'writer', 'cast', 'poster',
                     'plot', 'fullplot', 'language', 'country', 'imdbrating'}
    keys = []
    c = 1

    # keys in first line
    for line in fp:
        keys = [key.lower().strip() for key in line.split('\t')]
        print keys
        break

    for line in fp:
        data = [value.strip() for value in line.split('\t')]
        di = dict(zip(keys, data))
        di = {required_key: di[required_key] for required_key in required_keys}
        di = dict((key, None) if value.strip() == "" else (key, value.decode('utf8', 'ignore')) for key, value in di.iteritems())

        if di['genre']:
            di['genre'] = [li.strip() for li in di['genre'].split(',')]

        if di['cast']:
            di['cast'] = [li.strip() for li in di['cast'].split(',')]

        if di['imdbrating']:
            di['imdbrating'] = float(di['imdbrating'])

        di['omdbid'] = di['id']
        di.pop('id')

        req = urllib2.Request(MOVIE_SERVICE_URL)
        req.add_header('Content-Type', 'application/json')

        urllib2.urlopen(req, json.dumps(di))

    fp.close()

if __name__ == '__main__':
    load_movies()
