package de.futjikato.mrwhiz;

/**
 * Created with IntelliJ IDEA.
 * User: Moritz
 * Date: 21.04.13
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
public enum LaunchOptions {
    HELP("-h", 0) {
        @Override
        public void process(String[] optionParameter) {}
    },

    DEBUG("-debug", 0) {
        @Override
        public void process(String[] optionParameter) {}
    },

    MAP("-map", 1) {
        @Override
        public void process(String[] optionParameter) {
            if(optionParameter == null || optionParameter.length != 1) {
                throw new IllegalArgumentException("Missing map argument");
            }

            App.setMapName(optionParameter[0]);
        }
    };

    private String optionKey;

    private String[] parameter;

    private int paramCount;

    LaunchOptions(String optionKey, int paramCount) {
        this.optionKey = optionKey;
        this.paramCount = paramCount;
    }

    private String getOptionKey() {
        return optionKey;
    }

    public int getParamCount() {
        return paramCount;
    }

    public static LaunchOptions getByOption(String optionKey) {

        if(optionKey == null) {
            throw new IllegalArgumentException("optionKey cannot be null");
        }

        for(LaunchOptions v : values()) {
            if(v.getOptionKey().equals(optionKey)) {
                return v;
            }
        }

        throw new IllegalArgumentException(String.format("Unknown option key `%s` given", optionKey));
    }

    public abstract void process(String[] optionParameter);
}
