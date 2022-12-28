package com.stefanpetcu.colourreplacer.tools;

import com.stefanpetcu.colourreplacer.domain.CanvasPixelDto;
import com.stefanpetcu.colourreplacer.domain.PixelInterface;

public class PaintBucket implements ColourReplacer {
    @Override
    public void replaceColour(PixelInterface pixel, String colour) {
        replaceColour(pixel, colour, pixel.getColour());
    }

    private void replaceColour(PixelInterface pixel, String colour, String originalColour) {
        ColourReplacer.super.replaceColour(pixel, colour);

        if (pixel instanceof CanvasPixelDto) {
            var topCanvasPixel = ((CanvasPixelDto) pixel).getTopCanvasPixel();
            var rightCanvasPixel = ((CanvasPixelDto) pixel).getRightCanvasPixel();
            var bottomCanvasPixel = ((CanvasPixelDto) pixel).getBottomCanvasPixel();
            var leftCanvasPixel = ((CanvasPixelDto) pixel).getLeftCanvasPixel();
            if (topCanvasPixel.isPresent() && topCanvasPixel.get().getColour().equals(originalColour)) {
                this.replaceColour(topCanvasPixel.get(), colour, originalColour);
            }
            if (rightCanvasPixel.isPresent() && rightCanvasPixel.get().getColour().equals(originalColour)) {
                this.replaceColour(rightCanvasPixel.get(), colour, originalColour);
            }
            if (bottomCanvasPixel.isPresent() && bottomCanvasPixel.get().getColour().equals(originalColour)) {
                this.replaceColour(bottomCanvasPixel.get(), colour, originalColour);
            }
            if (leftCanvasPixel.isPresent() && leftCanvasPixel.get().getColour().equals(originalColour)) {
                this.replaceColour(leftCanvasPixel.get(), colour, originalColour);
            }
        }
    }
}
