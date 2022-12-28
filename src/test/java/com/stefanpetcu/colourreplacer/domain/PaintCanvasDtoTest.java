package com.stefanpetcu.colourreplacer.domain;

import com.stefanpetcu.colourreplacer.tools.PaintBucket;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap.SimpleImmutableEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * TODO: Improve tests, which are now awfully hard to follow.
 */
class PaintCanvasDtoTest {
    private PaintCanvasDto canvas;

    @BeforeEach
    void setUp() {
        canvas = new PaintCanvasDto(4, 3, new PaintBucket());

        canvas.drawWithColourOnPixelsAt("#000",
                new SimpleImmutableEntry<>(0, 2),
                new SimpleImmutableEntry<>(1, 1),
                new SimpleImmutableEntry<>(2, 1),
                new SimpleImmutableEntry<>(3, 0),
                new SimpleImmutableEntry<>(4, -1)); // Intentionally going out of bounds;
    }

    @Test
    void testDrawWithColourOnPixelsAtYieldsAWhiteMatrixWithABlackLineDrawnDiagonallyFromTheTopRightToTheBottomLeft() {
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                var pixel = canvas.getPixelAt(new SimpleImmutableEntry<>(y, x));
                assertTrue(pixel.isPresent());
                if ((y == 0 && x == 2) || (y == 1 && x ==1) || (y == 2 && x ==1) || (y == 3 && x == 0)) {
                    assertEquals("#000", pixel.get().getColour(),
                           String.format("Pixel at (%s, %s) was expected to be black; is %s instead.", y, x, pixel.get().getColour()));
                } else {
                    assertEquals("#ffffff", pixel.get().getColour(),
                            String.format("Pixel at (%s, %s) was expected to be white; is %s instead.", y, x, pixel.get().getColour()));
                }
            }
        }
    }

    @Test
    void testApplyPaintBucketWithColourOnPixelAtYieldsThePreviousMatrixButNowTheBottomRightHalfIsFilledWithRed() {
        canvas.applyPaintBucketWithColourOnPixelAt("#ff0000", new SimpleImmutableEntry<>(3, 2));
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 3; x++) {
                var pixel = canvas.getPixelAt(new SimpleImmutableEntry<>(y, x));
                assertTrue(pixel.isPresent());
                if ((y == 0 && x < 2) || (y == 1 && x == 0) || (y == 2 && x == 0)) {
                    assertEquals("#ffffff", pixel.get().getColour(),
                            String.format("Pixel at (%s, %s) was expected to be white; is %s instead.", y, x, pixel.get().getColour()));
                } else if ((y == 0 && x == 2) || (y == 1 && x ==1) || (y == 2 && x == 1) || (y == 3 && x == 0)) {
                    assertEquals("#000", pixel.get().getColour(),
                            String.format("Pixel at (%s, %s) was expected to be black; is %s instead.", y, x, pixel.get().getColour()));
                } else {
                    assertEquals("#ff0000", pixel.get().getColour(),
                            String.format("Pixel at (%s, %s) was expected to be red; is %s instead.", y, x, pixel.get().getColour()));
                }
            }
        }
    }
}
