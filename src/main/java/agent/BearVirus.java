package agent;

import com.main.Virologist;

public class BearVirus extends Agent {

    /**
     * Az ágens konstruktora
     *
     * @param id                - Az objektum azonosítója (az Effect ősosztály tárolja)
     * @param owner             - Az effect tulajdonosa
     */
    public BearVirus(String id, Virologist owner) {
        super(id, -1, owner, 0, 0);
    }

    /**
     * @param v az új effekt tulajdonosa
     * @return ezzel megegyező effektet ad vissza
     */
    public Agent CreateEffectFor(Virologist v) {
        return new BearVirus(id, v);
    }

    /**
     * Lépteti az effektet, ami véletlenszerű mozgásra készteti a virológust
     */
    @Override
    public void Step(){
        owner.MoveRandom();
    }

    /**
     * @return Igazat ad vissza, mivel ez medvevírus és terjeszteni kell
     */
    @Override
    public boolean SpreadBearVirus(){
        return true;
    }
}
