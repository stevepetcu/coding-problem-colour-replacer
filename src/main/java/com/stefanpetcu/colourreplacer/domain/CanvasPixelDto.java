package com.stefanpetcu.colourreplacer.domain;

import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
public class CanvasPixelDto implements PixelInterface {
    private String colour;
    private Optional<PixelInterface> topCanvasPixel = Optional.empty();
    private Optional<PixelInterface> rightCanvasPixel = Optional.empty();
    private Optional<PixelInterface> bottomCanvasPixel = Optional.empty();
    private Optional<PixelInterface> leftCanvasPixel = Optional.empty();

    @Override
    public String getColour() {
        return this.colour != null ? this.colour : PixelInterface.super.getColour();
    }

    @Override
    public void setColour(String colour) {
        this.colour = colour;
    }

    public Optional<PixelInterface> getTopCanvasPixel() {
        return topCanvasPixel;
    }

    public void setTopCanvasPixel(CanvasPixelDto topCanvasPixel) {
        this.topCanvasPixel = Optional.of(topCanvasPixel);
        if (topCanvasPixel.bottomCanvasPixel.isEmpty() || topCanvasPixel.bottomCanvasPixel.get() != this) {
            topCanvasPixel.setBottomCanvasPixel(this);
        }
    }

    public Optional<PixelInterface> getRightCanvasPixel() {
        return rightCanvasPixel;
    }

    public void setRightCanvasPixel(CanvasPixelDto rightCanvasPixel) {
        this.rightCanvasPixel = Optional.of(rightCanvasPixel);
        if (rightCanvasPixel.leftCanvasPixel.isEmpty() || rightCanvasPixel.leftCanvasPixel.get() != this) {
            rightCanvasPixel.setLeftCanvasPixel(this);
        }
    }

    public Optional<PixelInterface> getBottomCanvasPixel() {
        return bottomCanvasPixel;
    }

    public void setBottomCanvasPixel(CanvasPixelDto bottomCanvasPixel) {
        this.bottomCanvasPixel = Optional.of(bottomCanvasPixel);
        if (bottomCanvasPixel.topCanvasPixel.isEmpty() || bottomCanvasPixel.topCanvasPixel.get() != this) {
            bottomCanvasPixel.setTopCanvasPixel(this);
        }
    }

    public Optional<PixelInterface> getLeftCanvasPixel() {
        return leftCanvasPixel;
    }

    public void setLeftCanvasPixel(CanvasPixelDto leftCanvasPixel) {
        this.leftCanvasPixel = Optional.of(leftCanvasPixel);
        if (leftCanvasPixel.rightCanvasPixel.isEmpty() || leftCanvasPixel.rightCanvasPixel.get() != this) {
            leftCanvasPixel.setRightCanvasPixel(this);
        }
    }
}
