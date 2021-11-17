# Rapport Labo 9 - Taquin

## Auteurs:
Jonathan Friedli, Valentin Kaelin

## Tables des matières

* [Introduction](#Introduction)
* [Point 1](#Point-1)
* [Implementation de la stack](#Implementation-de-la-stack)
* [Conclusion](#Conclusion)

## Introduction
Ce laboratoire porte sur la tour de Hanoï. Nous devons calculer le temps que cela prendrait de le résoudre en faisant un mouvement par seconde. Nous devons ensuite implémenter en stack puis simuler une tour de Hanoï grace à l'UI qui a été fournie.

## Point 1
Le nombre de déplacements pour résoudre la tour de Hanoï vaut 2<sup>n</sup>-1, avec n le nombre de disque de la tour. Dans notre cas, n vaut 64 donc il faut 2<sup>64</sup>-1 déplacements. Ce qui vaut 18'446'744'073'709'552'000 déplacements (merci wikipédia). 

Les moines faisant un déplacement par seconde il faudrait 18'446'744'073'709'552'000 secondes. L'univers ayant environ 13.8 milliards d'année et vu que les moines n'existait pas (à ma connaissance) au début de l'univers. Nous allons supposer que le premier déplacement a eu lieu il y a exactement 13.8 millard d'années. Nous soustrayons donc 13'800'000'000 * 365,25 * 24 * 3600 à 18'446'744'073'709'552'000 ce qui donne: 
18'446'744'073'709'552'000 - 435'494'880'000'000'000 = 18'011'249'193'709'552'000 secondes restantes ce qui représente environ 570'742'046'090 années. Donc environ 570,7 milliards d'années.

## Implementation de la stack

## Conclusion