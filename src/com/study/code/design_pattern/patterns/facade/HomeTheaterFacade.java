package com.design.patterns.facade;


/**
 * @ClassName: HomeTheaterFacade
 * @Description:
 * @Author: jiel
 * @Date: 2022/4/10 17:08
 **/
public class HomeTheaterFacade {
    // 定义各个子系统对象
    private TheaterLight theaterLight;

    private Popcorn popcorn;
    private Stereo stereo;
    private Projector projector;
    private Screen screen;
    private DvdPlayer dvdPlayer;

    //构造器
    public HomeTheaterFacade() {
        super();
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.stereo = Stereo.getInstance();
        this.screen = Screen.getInstance();
        this.projector = Projector.getInstance();
        this.dvdPlayer = DvdPlayer.getInstance();
    }

    //操作分成4步
    public void ready() {
        popcorn.on();
        popcorn.poping();
        screen.down();
        projector.on();
        stereo.on();
        dvdPlayer.on();
        theaterLight.dim();
    }

    public void play() {
        dvdPlayer.play();
    }

    public void pause() {
        dvdPlayer.pause();
    }

    public void end() {
        popcorn.off();
    }
}
