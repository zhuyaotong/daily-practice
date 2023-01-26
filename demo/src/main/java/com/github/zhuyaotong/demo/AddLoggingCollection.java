package com.github.zhuyaotong.demo;

import com.google.common.collect.ForwardingCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class AddLoggingCollection<E> extends ForwardingCollection<E> {
    private static final Logger logger = LoggerFactory.getLogger(AddLoggingCollection.class);
    private Collection<E> originalCollection;

    public AddLoggingCollection(Collection<E> originalCollection) {
        this.originalCollection = originalCollection;
    }

    @Override
    protected Collection<E> delegate() {
        return this.originalCollection;
    }

    @Override
    public boolean add(E e) {
        logger.info("Add element: {}", e);
        return this.delegate().add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        logger.info("Size of elements to add: {}", collection.size());
        return this.delegate().addAll(collection);
    }
}
