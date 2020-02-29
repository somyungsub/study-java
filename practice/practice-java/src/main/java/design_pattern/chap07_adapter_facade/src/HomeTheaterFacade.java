package design_pattern.chap07_adapter_facade.src;

public class HomeTheaterFacade {
    HomeTheaterDto homeTheaterDto;

    public HomeTheaterFacade(HomeTheaterDto homeTheaterDto) {
        this.homeTheaterDto = homeTheaterDto;
    }

    public void watchMovie(String movie) {

        System.out.println("Get ready to watch a movie : " + movie);

        Amplifier amp = homeTheaterDto.getAmp();
        DvdPlayer dvdPlayer = homeTheaterDto.getDvdPlayer();

        amp.on();
        amp.setDvd(dvdPlayer);
        amp.setVolume(5);

        dvdPlayer.on();
        dvdPlayer.play(movie);

    }


    public void endMovie() {
        System.out.println("Shutting movie theater down....");

        Amplifier amp = homeTheaterDto.getAmp();
        DvdPlayer dvdPlayer = homeTheaterDto.getDvdPlayer();

        amp.off();
        dvdPlayer.stop();
        dvdPlayer.off();
    }
}
