'''
Created on Oct 17, 2016

@author: henrylevy
'''
import tornado, os
from tornado import netutil, process, httpserver
from Controller.AbstractBaseHandler import MainHandler
from Controller.UserHandlers.SinginHandler import SigninHandler
from tornado.ioloop import IOLoop
from Controller.UserHandlers.SingupHandler import SignUpHandler
from Controller.SpotHandlers.PostSpotHandler import PostSpotHandler
from Controller.SpotHandlers.SearchSpotHandler import SearchSpotHandler
from Controller.SpotHandlers.BookSpotHandler import BookSpotHandler
from Controller.SpotHandlers.DeleteSpotHandler import DeleteSpotHandler
from Controller.SpotHandlers.ViewPostingsHandler import ViewPostingsHandler
from Controller.SpotHandlers.ViewRentalsHandler import ViewRentalsHandler
from Controller.SpotHandlers.ViewSpotHandler import ViewSpotHandler
from Controller.UserHandlers.ModifyUserProfileHandler import ModifyUserProfileHandler
from Controller.UserHandlers.RateUserHandler import RateUserHandler
from Controller.UserHandlers.ViewUserProfileHandler import ViewUserProfileHandler
from Controller.SpotHandlers.GetClientTokenHandler import GetClientTokenHandler
settings = {
    "cookie_secret": "ADSFGHARY3457fgSDFHSDFjusdfASDFGH2345h=sdg",
    "login_url": "/signin",
}
data_dir = '/Users/henrylevy/ParkHere/Backend/'
application = tornado.web.Application([
    ("/", MainHandler),
    ("/signin", SigninHandler),
    ("/signup", SignUpHandler),
    ("/book/spot", BookSpotHandler),
    ("/delete/spot", DeleteSpotHandler),
    ("/search/spot", SearchSpotHandler),
    ("/view/postings", ViewPostingsHandler),
    ("/view/rentals", ViewRentalsHandler),
    ("/view/spot", ViewSpotHandler),
    ("/view/user", ViewUserProfileHandler),
    ("/modify/user", ModifyUserProfileHandler),
    ("/rate/user", RateUserHandler),
    ("/post/spot", PostSpotHandler),
    ("/get/token", GetClientTokenHandler)
], ssl_options={
    "certfile": os.path.join(data_dir, "server.crt"),
    "keyfile": os.path.join(data_dir, "server.key"),
}, **settings)

def main():
    sockets = netutil.bind_sockets(8888)
    process.fork_processes(0)
    server = httpserver.HTTPServer(application)
    server.add_sockets(sockets)
    IOLoop.current().start()

if __name__ == "__main__":
    main()