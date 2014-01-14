package example.cli;

import example.util.TesterMode;

public class CLIOptions implements ICLIOptions{

    private CLIOptionsStore delegate;

    public CLIOptions() {
        delegate = new CLIOptionsStore();
    }

    public void addOption(String name, Object value) {
        delegate.addOption(name, value);
    }

    @Override
    public String getAddress() {
        return delegate.getStringValue(CLIOptionNameBinding.ADDRESS);
    }

    @Override
    public String getImplementation() {
        return delegate.getStringValue(CLIOptionNameBinding.IMPLEMENTATION);
    }

    @Override
    public Integer getPort() {
        return delegate.getIntValue(CLIOptionNameBinding.PORT);
    }

    @Override
    public Integer getMessageNumber() {
        return delegate.getIntValue(CLIOptionNameBinding.MESSAGE_NUMBER);
    }

    @Override
    public Integer getMessageSize() {
        return delegate.getIntValue(CLIOptionNameBinding.MESSAGE_SIZE);
    }

    @Override
    public Integer getThreads() {
        return delegate.getIntValue(CLIOptionNameBinding.THREADS);
    }

    @Override
    public TesterMode getTesterMode() {
        return (TesterMode) delegate.getValue(CLIOptionNameBinding.TESTERMODE);
    }

    @Override
    public Boolean isJsonClientBuffering() {
        return delegate.getBooleanValue(CLIOptionNameBinding.IS_JSON_CLIENT_BUFFERING);
    }

    @Override
    public Integer getNettyClientBufferSize() {
        return delegate.getIntValue(CLIOptionNameBinding.NETTY_CLIENT_BUFFER_SIZE);
    }
}
