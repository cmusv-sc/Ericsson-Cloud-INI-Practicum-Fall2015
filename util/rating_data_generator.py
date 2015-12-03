import urllib2
import json
import random

USER_NUMBER = 10000
MOVIE_NUMBER = 1125135
RATINGS_SERVICE_URL = "http://localhost:6060/movie"

def gen_ratings():
    for i in range(1,MOVIE_NUMBER + 1):
        di = dict()
        userid = random.randint(1, USER_NUMBER)
        score = random.randint(1, 10)
        di["id"] = str(i)
        di["userId"] = str(userid)
        di["movieId"] = str(i)
        di["rating"] = str(score)
        req = urllib2.Request(RATINGS_SERVICE_URL)
        req.add_header('Content-Type', 'application/json')
        urllib2.urlopen(req, json.dumps(di))
        print str(i) + "/1125135"

if __name__ == '__main__':
    gen_ratings()
