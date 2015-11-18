import urllib2
import json

MOVIE_FILE = "/home/ec2-user/omdbFull.txt"
MOVIE_SERVICE_URL = "http://54.174.73.91:5050/movie/"

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
    count = 0
    for line in fp:
        data = [value.strip() for value in line.split('\t')]
        if data.__len__() >= keys.__len__():
            di = dict(zip(keys, data))
            di = {required_key: di[required_key] for required_key in required_keys}
            di = dict((key, None) if value.strip() == "" else (key, value.decode('utf8', 'ignore')) for key, value in di.iteritems())

            if di['genre']:
                di['genre'] = [li.strip() for li in di['genre'].split(',')]

            if di['cast']:
                di['cast'] = [li.strip() for li in di['cast'].split(',')]

            if di['imdbrating']:
                di['imdbrating'] = float(di['imdbrating'])

            #di['omdbid'] = di['id']
            #di.pop('id')
            #print json.dumps(di)
            req = urllib2.Request(MOVIE_SERVICE_URL)
            req.add_header('Content-Type', 'application/json')
            urllib2.urlopen(req, json.dumps(di))
            print str(count) + "/1125135"
            count += 1
        else:
            print line
    fp.close()



if __name__ == '__main__':
    load_movies()
