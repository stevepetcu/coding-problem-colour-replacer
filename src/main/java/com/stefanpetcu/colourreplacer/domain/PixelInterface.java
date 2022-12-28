package com.stefanpetcu.colourreplacer.domain;

public interface PixelInterface {
    /**
     * @return String White by default.
     */
    default String getColour() {
        return "#ffffff";
    }

    void setColour(String colour);
}
