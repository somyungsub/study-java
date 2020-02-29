package design_pattern.chap07_adapter_facade.src;

public class HomeTheaterDto {
    Amplifier amp;
    Tunner tunner;
    DvdPlayer dvdPlayer;
    CdPlayer cdPlayer;

    public Amplifier getAmp() {
        return amp;
    }

    public void setAmp(Amplifier amp) {
        this.amp = amp;
    }

    public Tunner getTunner() {
        return tunner;
    }

    public void setTunner(Tunner tunner) {
        this.tunner = tunner;
    }

    public DvdPlayer getDvdPlayer() {
        return dvdPlayer;
    }

    public void setDvdPlayer(DvdPlayer dvdPlayer) {
        this.dvdPlayer = dvdPlayer;
    }

    public CdPlayer getCdPlayer() {
        return cdPlayer;
    }

    public void setCdPlayer(CdPlayer cdPlayer) {
        this.cdPlayer = cdPlayer;
    }
}
