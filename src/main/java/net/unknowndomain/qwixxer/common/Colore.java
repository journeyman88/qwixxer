/*
 * Copyright 2018 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
