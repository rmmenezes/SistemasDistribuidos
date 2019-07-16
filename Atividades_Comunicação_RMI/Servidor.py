import Pyro4
from Locadora import Locadora

daemon = Pyro4.Daemon()                  
ns = Pyro4.locateNS()                    #localiza o servidor de nomes
uri = daemon.register(Locadora)          #registra Locadora como um objeto Pyro
ns.register("LocadoraService", uri)      
daemon.requestLoop()                     #aguardando