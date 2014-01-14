package example.cli;

import example.util.TesterMode;

public interface ICLIOptions {

    public String getAddress();

    public String getImplementation();

    public Integer getPort();

    public Integer getMessageNumber();

    public Integer getMessageSize();

    public Integer getThreads();

    public TesterMode getTesterMode();

    public Boolean isJsonClientBuffering();

    public Integer getNettyClientBufferSize();
}
