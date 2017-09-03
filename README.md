# Boter-Kaas-en-Eieren
Het spelletje Boter Kaas en Eieren waarbij mens tegen de computer (hierna agent genoemd) speelt. Geïmplementeerd in Java

Boter-kaas-en-eieren is een spel met twee spelers: een speler gebruikt het teken X en de andere het teken O. Iedere speler doet om de beurt een zet. De speler met het teken X begint het spel. De spelers kunnen een zet alleen doen in de lege vakjes. Het speelveld bestaat uit 9 lege vakjes die gegroepeerd zijn als een bord van drie bij drie vakjes. Degene die drie van zijn eigen tekens op een rij heeft (diagonaal, verticaal of horizontaal), heeft gewonnen. Als het speelveld vol is en de spelers dit doel niet hebben bereikt, dan is het een gelijkspel. 

Er zijn drie niveaus van intelligentie geprogrammeerd. 
1. De agent kiest steeds een willekeurige zet
2. De agent kiest op basis van een analyse van kansen en bedreigingen, maar kijkt NIET een stap vooruit.
3. De agent denkt eerst een aantal stappen vooruit en analyseert dan pas kansen en bedreigingen. Dit moet nog geïmplenteerd worden.

Bij niveau 2 analyseert de agent de opstelling via het volgende algoritme:

1. +100 voor IEDERE 3-op-rij voor de agent
2. +10 voor IEDERE 2-op-een-rij voor de agent
3. +1 voor IEDERE 1-op-een-rij voor de agent.

De tegenstander (De gebruiker dus) krijgt negatieve scores. -100,-10,-1 voor 3-op-eenrij,
2-op-een-rij of 1-op-een rij.

De score van de opstelling wordt berekend door alle rij-scores op te tellen voor de 8 lijnen in het spel (3 rijen, 3 kolommen en 2 diagonalen) 

In de code komen een aantal begrippen steeds terug:

1. gebruiker: de persoon achter de computer (U dus)
2. agent: verzameling algortimes die beslissen wat de beste zet is
3. symbool: een teken om de agent en de gebruiker te onderscheidden (X of een O)
4. bord: mogelijke plaatsen waar een symbool geplaats kan worden
5. opstelling: een combinatie van agent en spelerssysmbolen.
6. zet: een beslissing om het symbool van de agent op een specifieke plek op bord te plaatsen. Leidt tot een  verandering in opstelling.
7. mogelijke zet: alle mogelijk zetten binnen de regels van het spel.
8. beste zet: een zet met de grootste kans op winnen voor de agent. Leidt tot de beste opstelling voor de agent.

   
