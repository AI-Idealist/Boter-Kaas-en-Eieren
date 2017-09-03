# Boter-Kaas-en-Eieren
Het spelletje Boter Kaas en Eieren waarbij mens tegen de computer (hierna agent genoemd) speelt. Geïmplementeerd in Java

Boter-kaas-en-eieren is een spel met twee spelers: een speler gebruikt het teken X en de andere het teken O. Iedere speler doet om de beurt een zet. De speler met het teken X begint het spel. De spelers kunnen een zet alleen doen in de lege vakjes. Het speelveld bestaat uit 9 lege vakjes die gegroepeerd zijn als een bord van drie bij drie vakjes. Degene die drie van zijn eigen tekens op een rij heeft (diagonaal, verticaal of horizontaal), heeft gewonnen. Als het speelveld vol is en de spelers dit doel niet hebben bereikt, dan is het een gelijkspel. 

Er zijn drie niveaus van intelligentie geprogrammeerd. 
1. De agent kiest steeds een willekeurige zet
2. De agent kiest op basis van een analyse van kansen en bedreigingen, maar kijkt NIET een stap vooruit.
3. De agent denkt eerst een aantal stappen vooruit en analyseert dan pas kansen en bedreigingen.

Bij niveau 2 analyseert de agent de opstelling via het volgende algoritme

    +100 for EACH 3-in-a-line for computer.
    +10 for EACH two-in-a-line (with a empty cell) for computer.
    +1 for EACH one-in-a-line (with two empty cells) for computer.
    Negative scores for opponent, i.e., -100, -10, -1 for EACH opponent's 3-in-a-line, 2-in-a-line and 1-in-a-line.
    0 otherwise (empty lines or lines with both computer's and opponent's seeds).

For Tic-Tac-Toe, compute the scores for each of the 8 lines (3 rows, 3 columns and 2 diagonals) and obtain the sum.
