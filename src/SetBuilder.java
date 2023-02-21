import javax.swing.border.LineBorder;



public class SetBuilder {

    public SetOfCards createSetOfCards(){
        SetOfCards setOfCards = new SetOfCards();
        setOfCards.addCard(new Card("A", "Pictures/A.jpg", "Sounds/Apa.wav"));
        setOfCards.addCard(new Card("B","Pictures/B.jpg","Sounds/Boll.wav"));
        setOfCards.addCard(new Card("C","Pictures/C.jpg","Sounds/Citron.wav"));
        setOfCards.addCard(new Card("D", "Pictures/D.jpg", "Sounds/Druva.wav"));
        setOfCards.addCard(new Card("E","Pictures/E.jpg","Sounds/Ekorre.wav"));
        setOfCards.addCard(new Card("F","Pictures/F.jpg","Sounds/Får.wav"));
        setOfCards.addCard(new Card("G", "Pictures/G.jpg", "Sounds/Glass.wav"));
        setOfCards.addCard(new Card("H","Pictures/H.jpg","Sounds/Hus.wav"));
        setOfCards.addCard(new Card("I","Pictures/I.jpg","Sounds/Is.wav"));
        setOfCards.addCard(new Card("J", "Pictures/J.jpg", "Sounds/Jojo.wav"));
        setOfCards.addCard(new Card("K","Pictures/K.jpg","Sounds/Katt.wav"));
        setOfCards.addCard(new Card("L","Pictures/L.jpg","Sounds/Lök.wav"));
        setOfCards.addCard(new Card("M", "Pictures/M.jpg", "Sounds/Måne.wav"));
        setOfCards.addCard(new Card("N","Pictures/N.jpg","Sounds/Napp.wav"));
        setOfCards.addCard(new Card("O","Pictures/O.jpg","Sounds/Orm.wav"));
        setOfCards.addCard(new Card("P", "Pictures/P.jpg", "Sounds/Pil.wav"));
        setOfCards.addCard(new Card("Q","Pictures/Q.jpg","Sounds/Qigong.wav"));
        setOfCards.addCard(new Card("R","Pictures/R.jpg","Sounds/Ring.wav"));
        setOfCards.addCard(new Card("S", "Pictures/S.jpg", "Sounds/Sol.wav"));
        setOfCards.addCard(new Card("T","Pictures/T.jpg","Sounds/Träd.wav"));
        setOfCards.addCard(new Card("U","Pictures/U.jpg","Sounds/Uggla.wav"));
        setOfCards.addCard(new Card("V", "Pictures/V.jpg", "Sounds/Val.wav"));
        setOfCards.addCard(new Card("W","Pictures/W.jpg","Sounds/Webbsida.wav"));
        setOfCards.addCard(new Card("X","Pictures/X.jpg","Sounds/Xylofon.wav"));
        setOfCards.addCard(new Card("Y", "Pictures/Y.jpg", "Sounds/Yxa.wav"));
        setOfCards.addCard(new Card("Z","Pictures/Z.jpg","Sounds/Zebra.wav"));
        setOfCards.addCard(new Card("Ä","Pictures/Ä.jpg","Sounds/Ägg.wav"));
        setOfCards.addCard(new Card("Å","Pictures/Å.jpg","Sounds/Åsna.wav"));
        setOfCards.addCard(new Card("Ö","Pictures/Ö.jpg","Sounds/Öken.wav"));
        return setOfCards;
    }
    public SetBuilder() {
    }
}