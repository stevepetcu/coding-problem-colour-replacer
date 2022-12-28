package com.stefanpetcu.colourreplacer.domain;

import com.stefanpetcu.colourreplacer.tools.PaintBucket;

import java.util.Map.Entry;
import java.util.Optional;

public class PaintCanvasDto {
    private final CanvasPixelDto[][] canvas;
    private final PaintBucket bucket;

    /**
     * x = horizontal position
     * y = vertical position
     *  @param height      Integer
     * @param width       Integer
     * @param paintBucket PaintBucket
     */
    public PaintCanvasDto(Integer height, Integer width, PaintBucket paintBucket) {
        canvas = new CanvasPixelDto[height][width];
        bucket = paintBucket;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                var pixel = new CanvasPixelDto();
                canvas[y][x] = pixel;

                if (y > 0) {
                    try { // Try/Catch might be a bit redundant here.
                        pixel.setTopCanvasPixel(canvas[y - 1][x]);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        continue;
                    }
                }
                if (x > 0) {
                    try { // Try/Catch might be a bit redundant here.
                        pixel.setLeftCanvasPixel(canvas[y][x - 1]);
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        continue;
                    }
                }
            }
        }
    }

    public final Optional<CanvasPixelDto> getPixelAt(Entry<Integer, Integer> location) {
        try {
            return Optional.of(canvas[location.getKey()][location.getValue()]);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return Optional.empty();
        }
    }

    @SafeVarargs
    public final void drawWithColourOnPixelsAt(String colour, Entry<Integer, Integer>... locations) {
        for (Entry<Integer, Integer> location : locations) {
            var pixelOptional = getPixelAt(location);
            pixelOptional.ifPresent(canvasPixelDto -> canvasPixelDto.setColour(colour));
        }
    }

    public final void applyPaintBucketWithColourOnPixelAt(String colour, Entry<Integer, Integer> location) {
        var pixelOptional = getPixelAt(location);
        pixelOptional.ifPresent(canvasPixelDto -> bucket.replaceColour(canvasPixelDto, colour));
    }
}
