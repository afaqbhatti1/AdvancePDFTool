package pdf.pdftool.interfaces;

import pdf.pdftool.model.OptionsEntityEnhancement;

/**
 * The {@link Enhancer} is a functional interface for all enhancements.
 */
public interface Enhancer {
    /**
     * To apply an enhancement.
     */
    void enhance();

    /**
     * @return The {@link OptionsEntityEnhancement} for this {@link Enhancer}.
     */
    OptionsEntityEnhancement getEnhancementOptionsEntity();
}
