package pl.training.camel.moduleone;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.springframework.stereotype.Component;

@Component
public class ConfigFileFilter<T> implements GenericFileFilter<T> {

    @Override
    public boolean accept(GenericFile<T> file) {
        return file.getFileName().endsWith("xml");
    }

}
