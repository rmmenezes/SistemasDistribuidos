import Pyro4
import sys

def main():
    Pyro4.locateNS("127.0.0.1", 9090)
    locadora = Pyro4.Proxy("PYRONAME:LocadoraService") #URI
    while(True):
        try:
            print("### LISTA DE AÇOES ### \n1 - Lista musicas \n2 - Adicionar musica \n3 - Apagar musica \n4 - Sair")
            opcao = input("\nEscolha uma Opção: ")
        except:
            print("\nOpção Invalida")
        if(opcao == "1"):
            dado = locadora.list_contents()
            print(dado)
        elif(opcao == "2"):
            name = input("\nDigite o Nome da Musica: ")
            band = input("\nDigite a Banda: ")
            genre = input("\nDigite o Gênero: ")
            add_music = locadora.add_music(name, band, genre)
            print(add_music)
        elif(opcao == "3"):
            music = int(input("\nDigite a posição da Musica: "))
            list_ = locadora.delete_music(music)
            print(list_)
        elif(opcao == "4"):
            break
        else:
            print("Infome uma opção valida")
            
if __name__ == "__main__":
    main()