package org.syaku.exemple.app.demo.service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.syaku.exemple.app.demo.domain.Demo;
import org.syaku.exemple.app.demo.domain.DemoEntity;
import org.syaku.exemple.app.demo.repository.DemoRepository;

import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @since 2018. 10. 1.
 */
@Service
@Transactional
public class WebDemoService {
    private DemoRepository demoRepository;

    @Autowired
    public void setDemoRepository(DemoRepository demoRepository) {
        this.demoRepository = demoRepository;
    }

    public List<Demo> getDemoList() {
        return Lists.transform(Lists.newArrayList(demoRepository.findAll()), new Function<DemoEntity, Demo>() {
            @Override
            public Demo apply(DemoEntity input) {
                return input;
            }
        });
    }
}
