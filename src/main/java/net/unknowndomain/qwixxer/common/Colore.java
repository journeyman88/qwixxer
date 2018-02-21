/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.qwixxer.common;

import net.unknowndomain.qwixxer.imaging.Locator;

/**
 *
 * @author journeyman
 */
public enum Colore {
    ROSSO( Locator.COLORS_PATH + "ZR.png", Locator.COLORS_PATH + "BR.png", Locator.COLORS_PATH + "LR.png"),
    VERDE( Locator.COLORS_PATH + "ZV.png", Locator.COLORS_PATH + "BV.png", Locator.COLORS_PATH + "LV.png"),
    GIALLO(Locator.COLORS_PATH + "ZG.png", Locator.COLORS_PATH + "BG.png", Locator.COLORS_PATH + "LG.png"),
    BLU(   Locator.COLORS_PATH + "ZB.png", Locator.COLORS_PATH + "BB.png", Locator.COLORS_PATH + "LB.png");
    
    Colore(String initImage, String bgImage, String lockedImage)
    {
        this.initImage = initImage;
        this.bgImage = bgImage;
        this.lockedImage = lockedImage;
    }
    
    private final String initImage;
    private final String bgImage;
    private final String lockedImage;

    public String getInitImage() {
        return initImage;
    }

    public String getBgImage() {
        return bgImage;
    }

    public String getLockedImage() {
        return lockedImage;
    }
}
