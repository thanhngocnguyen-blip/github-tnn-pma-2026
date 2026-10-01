Tento repozitář obsahuje dvě verze aplikace pro házení kostkou. Obě varianty splňují stejné zadání (animace 10 hodů s prodlevou, blokace tlačítka), ale každá na to jde z programátorského hlediska úplně jinak.

Porovnání:

1. Starší přístup (XML + Kotlin s findViewById)
   V této verzi je nutné dělat věci manuálně. Uživatelské rozhraní a logika jsou oddělené. Když chci po hodu změnit symbol kostky na displeji, musím nejdřív příslušný textový prvek fyzicky najít v kódu pomocí findViewById. Poté mu musím natvrdo přiřadit novou hodnotu (např. tvDice.text = "⚁"). Změnu UI tedy musím odřídit krok za krokem já sám.

2. Moderní přístup (Jetpack Compose)
   Tady se nic nevyhledává. Compose funguje na principu sledování "stavu" (State). V kódu mám nadefinovanou proměnnou reprezentující aktuální hodovou hodnotu kostky. Jakmile do této proměnné zapíšu nové číslo, Jetpack Compose si změny sám všimne a automaticky překreslí tu část obrazovky, která na tento stav spoléhá. Zatímco v XML musím prvku přikázat, ať se změní, v Compose pouze změním data a rozhraní už se přizpůsobí samo.