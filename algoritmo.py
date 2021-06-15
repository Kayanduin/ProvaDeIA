import random
import numpy as np
import math

#Definição de funções

def gerarPesos():
    arrayPesos = []
    i = 0
    while i < 10 :
        arrayPesos.append(np.random.random((10)))
        i += 1
    return arrayPesos
    
def gerarEntradas():
    return np.random.random((10))

def gerarIdeais():
    return np.random.random((10))
    
def somatorio(entradas,pesos):
    somatorio = 0
    i = 0
    for entrada in entradas:
        somatorio += entrada * pesos[i]
        i += 1
    return somatorio

def custo(obtidos,ideais):
    custoTotal = 0
    i = 0
    for obtido in obtidos:
        custoTotal += math.pow((obtido + ideais[i]), 2)
        i += 1
    custoTotal = round(custoTotal, 2)
    return custoTotal

def obtidoEhIdeal (custoAtual, custoIdeal):
    
    if(custoAtual > custoIdeal):
        return False
    
    return True

def calibrarPesos(entradas, obtidos):
    novosPesos = []
    for obtidoAntigo in obtidos:
        peso = np.random.random((10))
        obtidoNovo = somatorio(entradas, peso)
        while obtidoAntigo < obtidoNovo:
            peso = np.random.random((10))
            obtidoNovo = somatorio(entradas, peso)
        novosPesos.append(peso)
    return novosPesos


#Main

def main ():
    
    custoIdeal = 54
    
    #calculando o custo inicial
    arrayPesos = gerarPesos()
    entradas = gerarEntradas()
    ideais = gerarIdeais()
    
    obtidos = []
    
    for pesos in arrayPesos:
        obtidos.append(somatorio(entradas,pesos))
        
    custoAtual = custo(obtidos, ideais)
    
    #Calculando o custo ideal
    
    novosPesos = calibrarPesos(entradas, obtidos)
    novosObtidos = []
    for pesoNovo in novosPesos:
        novosObtidos.append(somatorio(entradas,pesoNovo))
        
    novoCusto = custo(novosObtidos, ideais)
    
    print("O valor do custo antigo é: ", custoAtual)

    i = 0
    while novoCusto > custoIdeal:
        
        novosPesos = calibrarPesos(entradas, novosObtidos)
        novosObtidos2 = []
        for pesoNovo in novosPesos:
            novosObtidos2.append(somatorio(entradas,pesoNovo))
            
        novoCusto = custo(novosObtidos2, ideais)
        print("Iteração ", i, "com valor de custo: ", novoCusto)
        i +=1
    
    print("O valor do custo novo é: ", novoCusto)
    
main()