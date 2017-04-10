package com.triquetra.datetime.tags;

/**
 * Created by sayef on 4/11/17.
 */
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessor;
import org.springframework.web.servlet.tags.form.SelectTag;
import org.springframework.web.servlet.tags.form.TagWriter;

import javax.servlet.jsp.JspException;
import java.util.Map;
import java.util.Objects;

public class SelectFieldTag extends SelectTag {
    private Map<String, Objects> dynAttrs;

    public void setDynAttrs(Map<String, Objects> dynAttrs) {
        this.dynAttrs = dynAttrs;
    }

    @Override
    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        PropertyAccessor accessor = new BeanWrapperImpl(this);
        if (accessor.isWritableProperty(localName)) {
            accessor.setPropertyValue(localName, value);
        } else {
            super.setDynamicAttribute(uri, localName, value);
        }
    }

    @Override
    protected int writeTagContent(TagWriter tagWriter) throws JspException {
        PropertyAccessor accessor = new BeanWrapperImpl(this);
        for (String key : this.dynAttrs.keySet()) {
            if (accessor.isWritableProperty(key)) {
                accessor.setPropertyValue(key, this.dynAttrs.get(key));
            } else {
                super.setDynamicAttribute(null, key, this.dynAttrs.get(key));
            }
        }
        return super.writeTagContent(tagWriter);
    }
}
