package design_pattern.chap07_adapter_facade.src;

public class HomeTheaterTestDrive {
    public static void main(String[] args) {
        HomeTheaterDto dto = new HomeTheaterDto();
        dto.setAmp(new Amplifier());
        dto.setCdPlayer(new CdPlayer());
        dto.setDvdPlayer(new DvdPlayer());
        dto.setTunner(new Tunner());

        HomeTheaterFacade facade = new HomeTheaterFacade(dto);
        facade.watchMovie("일곱개의 대죄");
        facade.endMovie();
    }
}
