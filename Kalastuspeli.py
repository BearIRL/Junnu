# Tuodaan eri listat KalastuspeliListat tiedostosta
from KalastuspeliListat import (kalaLista0to10, kalaLista10to20,
kalaLista20to50, kalaLista50to100, kalaLista100to1000,
uistinLista, uistinPainot, pohjaLoot)
# Tuodaan random kirjastosta funktioita
from random import choice, randint

# Aliohjelmien määritys:
# 'aloitus()', esitellään peli ja kysytään tarvitseeko avata ohjeita
def aloitus():
    print("---")
    print("Kalastuspeli V.1:")
    print("Tekijä: Juhana Moilanen 27.1.2020")
    print("---")
    print("")
    valmis = input("Tarvitsetko kalamiehen lisäohjeita? joo/ei: ")
    print("")
    if valmis == "joo":
        ohje()

# 'ohje()', annetaan pelaajalle ohjeet ja miten peli laskee syvyyden ym.
def ohje():
    print("Syvyys:")
    print("Joka metri, joka soudetaan poispäin rannasta, vesi syvenee puoli metriä")
    print("Uistimen valinta:")
    print("Tarkasti laskettuna, uistin uppoaa metrin syvemmälle jokaista 6 grammaa kohti.")
    print("Liian kevyellä uistimella ei saada saalista syvissä vesissä!")
    print("Jos uistin jää kiinni pohjaan, saat tyytyä supermarketin kalatiskin kaloihin.")
    print("")

# 'valitseSyvyys()', pelaaja valitsee miten pitkälle soudetaan rannasta
# jokaista (metriä) kohti vesi syvenee 1 metrin; talletetaan 'syvyys'
def valitseSyvyys():
    print("Aloitetaan siiten...")
    t = True
    while t:
        try:
            matka = int(input("Miten pitkälle soudetaan rannasta: "))
            syvyys = matka / 2
            t = False
            return(syvyys)
        except:
            print("Syötä syvyys (ei mitään muuta)!")
        finally:
            print("")

# 'vallitseUistin()', esitellään lista-tiedostosta uistimet
# pelaaja valitsee näistä yhden syöttämällä sen numeron
# valitun uistimen paino otetaan toisesta listasta ja talletetaan 'uistinPaino'
def valitseUistin():
    print("---")
    print("Valittavat uistimet:")
    for uistin in uistinLista:
        print(uistin)
    t = True
    while t:
        try:
            valittuUistin = int(input("Minkä uistimen valitset: "))
            if valittuUistin == 0:
                print("Väärä syöte.")
                continue
            uistinPaino = uistinPainot[valittuUistin -1]
            t = False
            return(uistinPaino)
        except:
            print("Väärä syöte.")
        finally:
            print("")


# 'mikaSaalis()', saa parametreina 'syvyys' ja 'uistinPaino'
# Tarkastetaan uppoaako uistin pohjaan (uistimen paino > syvyys)
# jolloin kutsutaan aliohjelma 'osuiPohjaan()'
# Sitten tarkistetaan jos 'uistinPaino' ja 'syvyys' ovat tietyn suuruisia, ja
# arvotaan sen mukaisesta listasta kala, ja
# määritetään mikä on saadun kalan suurin ja pienin mahdollinen paino
# ja 'arvoKalanPaino()' avulla arvotaan saadun kalan paino, ja
# luodaan 'loot' merkkijono, johon talletetaan saadun kalan nimi ja saatu paino
def mikaSaalis(syvyys, uistinPaino):
    if uistinPaino > syvyys:
        osuiPohjaan()
    else:
        if uistinPaino > 1000 and syvyys > 1000:
            print("Sinusta tuli saalis.")
            print("Sinut syötiin. Kuolit.")
        elif uistinPaino > 100 and syvyys > 100:
            loot = choice(kalaLista100to1000)
            minPaino = 80
            maxPaino = 150
            kalanPaino = arvoKalanPaino(minPaino, maxPaino)
            loot = loot + " " + str(kalanPaino) + " kg"
            saiKalan(loot)
        elif uistinPaino > 50 and syvyys > 50:
            loot = choice(kalaLista50to100)
            minPaino = 18
            maxPaino = 35
            kalanPaino = arvoKalanPaino(minPaino, maxPaino)
            loot = loot + " " + str(kalanPaino) + " kg"
            saiKalan(loot)
        elif uistinPaino > 20 and syvyys > 20:
            loot = choice(kalaLista20to50)
            minPaino = 5
            maxPaino = 15
            kalanPaino = arvoKalanPaino(minPaino, maxPaino)
            loot = loot + " " + str(kalanPaino) + " kg"
            saiKalan(loot)
        elif uistinPaino > 10 and syvyys > 10:
            loot = choice(kalaLista10to20)
            minPaino = 1
            maxPaino = 3
            kalanPaino = arvoKalanPaino(minPaino, maxPaino)
            loot = loot + " " + str(kalanPaino) + " kg"
            saiKalan(loot)
        elif uistinPaino <= 10 and syvyys <= 10:
            loot = choice(kalaLista0to10)
            minPaino = 100
            maxPaino = 300
            kalanPaino = arvoKalanPaino(minPaino, maxPaino)
            loot = loot + " " + str(kalanPaino) + " g"
            saiKalan(loot)
        else:
            print("Uistimesi oli liian kevyt saamaan mitään noin syvistä vesistä.")

# 'arvoKalanPaino()', arpoo annetun minimi- ja maksimi-arvon välistä luvun,
# joka palautetaan
def arvoKalanPaino(minPaino, maxPaino):
    kalanPaino = randint(minPaino, maxPaino)
    return(kalanPaino)

# 'osuiPohjaan()' arpoo pohjaLoot-listasta mitä pelaaja saa
# ja kertoo sen
def osuiPohjaan():
    loot = choice(pohjaLoot)
    print("Osuit pohjaan.")
    print("Sait", loot, "Olet pettymys koko suvullesi.")

# 'saiKalan()', kertoo pelaajalle pelaajan saaman saaliin
def saiKalan(loot):
    print("")
    print("Saamasi saalis: ", loot)
    print("Ehkä olet sittenkin oikea kalamies.")


# Kutsutaan aliohjelmat (peli alkaa)
aloitus()
syvyys = valitseSyvyys()
uistinPaino = valitseUistin()
mikaSaalis(syvyys, uistinPaino)

# input-kometo jotta ohjelma pysähtyy
input("")

