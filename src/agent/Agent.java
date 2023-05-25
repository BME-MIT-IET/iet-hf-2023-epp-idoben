package agent;

import com.main.Skeleton;
import com.main.Virologist;

/**
 * Absztrakt ágens osztály, mely a pályán megtanulható kódként jelenik meg, a virológus ezt ismeri mint megtanult
 * ágens, ennek a segítségével tesz rá a másik virológusra ágenst.
 */
public abstract class Agent extends Effect{
    /**
     * Az ágens elkészítéséhez szükséges nukleotid szám
     */
    public int nucleotidRequired;
    /**
     * Az ágens elkészítéséhez szükséges aminosav szám
     */
    public int aminoacidRequired;

    /**
     * Az ágens konstruktora
     * @param id - Az objektum azonosítója (az Effect ősosztály tárolja)
     * @param timeToLive - Az ágens élettartama, amennyiben azt valakire Effect-ként rakták rá
     * @param owner - Az effect tulajdonosa, amennyiben ez egy pályán lévő megtanulható kód akkor null
     * @param nucleotidRequired - az ágens elkészítéséhez szükséges nukleotid szám
     * @param aminoacidRequired - az ágens elkészítéséhez szükséges aminosav szám
     */
    public Agent(String id, int timeToLive, Virologist owner, int nucleotidRequired, int aminoacidRequired) {
        super(id, timeToLive, owner);
        this.nucleotidRequired = nucleotidRequired;
        this.aminoacidRequired = aminoacidRequired;
    }

    /**
     * Absztrakt függvény, mely visszaad egy ezzel azonos típúsú ágenst
     * @param v - az ágens tulajdonosa, amennyiben null, úgy az ágens nem Effect-ként van használva
     * @return a létrehozott ágens
     */
    public abstract Agent CreateEffectFor(Virologist v);

    /**
     * Ágens kenése másik virológusra
     * @param v - a másik virológus
     * @param from - az a virológus, aki keni az ágenst
     */
    public void UseOn(Virologist v, Virologist from){
        Skeleton.functionCall("Agent.UseOn(v: Virologist, from: Virologist)  ID: " + id);
        v.ReceiveEffectFrom(from, CreateEffectFor(v));
        Skeleton.functionReturn("Agent.UseOn(v: Virologist, from: Virologist)  Return");
    }
}
