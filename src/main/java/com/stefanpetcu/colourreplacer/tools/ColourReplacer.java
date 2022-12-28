package com.stefanpetcu.colourreplacer.tools;

import com.stefanpetcu.colourreplacer.domain.CanvasPixelDto;
import com.stefanpetcu.colourreplacer.domain.PixelInterface;

public interface ColourReplacer {
    default public void replaceColour(PixelInterface pixel, String colour) {
        pixel.setColour(colour);
    }
}
