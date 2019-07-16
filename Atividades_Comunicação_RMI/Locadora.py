import Pyro4

@Pyro4.expose
@Pyro4.behavior(instance_mode="single")
class Locadora:
    def __init__(self):
        self.contents = []
    
    def list_contents(self):
        return self.contents

    def add_music(self, name, band, genre):
        music = {
            'name': name,
            'band': band,
            'genre': genre
        }
        self.contents.append(music)
        print('Adicionado', music)
        return music
    
    def delete_music(self, index):
        del self.contents[index]
        print(self.contents)
        return self.contents
    