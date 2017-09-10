# Boter-Kaas-en-Eieren
Het spelletje Boter Kaas en Eieren waarbij mens tegen de computer (hierna agent genoemd) speelt. Geïmplementeerd in Java

Boter-kaas-en-eieren is een spel met twee spelers: een speler gebruikt het teken X en de andere het teken O. Iedere speler doet om de beurt een zet. De speler met het teken X begint het spel. De spelers kunnen een zet alleen doen in de lege vakjes. Het speelveld bestaat uit 9 lege vakjes die gegroepeerd zijn als een bord van drie bij drie vakjes. Degene die drie van zijn eigen tekens op een rij heeft (diagonaal, verticaal of horizontaal), heeft gewonnen. Als het speelveld vol is en de spelers dit doel niet hebben bereikt, dan is het een gelijkspel. 

Er zijn vier niveaus van intelligentie geprogrammeerd.
1. De agent is erg doem en vraagt wat aan de gebruiker de beste zet.
2. De agent kiest steeds een willekeurige zet
3. De agent kiest op basis van een analyse van kansen en bedreigingen met slechts de volgende stap in gedachten.
4. De agent denkt eerst een aantal stappen vooruit en analyseert kansen en bedreigingen. 

Bij niveau 3 analyseert de agent de kansen en bedreigingen van opstelling opstelling via het volgende algoritme:

De kansen (kans in de betekenis van kracht) worden als volgt berekent: 
1. +100 voor IEDERE 3-op-rij voor de agent
2. +10 voor IEDERE 2-op-een-rij voor de agent
3. +1 voor IEDERE 1-op-een-rij voor de agent.

De tegenstander (De gebruiker dus) krijgt negatieve scores. -100,-10,-1 voor 3-op-eenrij,
2-op-een-rij of 1-op-een rij.

De score van de opstelling wordt berekend door alle rij-scores op te tellen voor de 8 lijnen in het spel (3 rijen, 3 kolommen en 2 diagonalen) 

Bij niveau 4 wordt gebruikt gemaakt van het MiniMax algoritme. zie https://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe_AI.html
