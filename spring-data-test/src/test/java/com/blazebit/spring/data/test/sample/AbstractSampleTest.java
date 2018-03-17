/*
 * Copyright 2014 - 2018 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blazebit.spring.data.test.sample;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.blazebit.spring.data.test.model.Husband;
import com.blazebit.spring.data.test.model.Wife;
import com.blazebit.spring.data.test.repository.WifeRepository;

@Transactional
public abstract class AbstractSampleTest {

    @Autowired
    protected EntityManager em;
    @Autowired
    protected WifeRepository wifeRepository;

    @Before
    public void init() {
        // Store a wife to DB
        Wife lisa = new Wife("Lisa", new Husband("David"));
        wifeRepository.save(lisa);

        // Store list wifes to DB
        Wife mary = new Wife("Mary", new Husband("Peter"));

        Wife lauren = new Wife("Lauren", new Husband("Phillip"));
        Wife lauren1 = new Wife("Lauren1", new Husband("Phillip1"));
        Wife lauren2 = new Wife("Lauren2", new Husband("Phillip2"));
        Wife lauren3 = new Wife("Lauren3", new Husband("Phillip3"));
        Wife lauren4 = new Wife("Lauren4", new Husband("Phillip4"));
        Wife lauren5 = new Wife("Lauren5", new Husband("Phillip5"));
        Wife lauren6 = new Wife("Lauren6", new Husband("Phillip6"));
        Wife lauren7 = new Wife("Lauren7", new Husband("Phillip7"));
        wifeRepository.save(Arrays.asList(mary, lauren, lauren1, lauren2, lauren3, lauren4, lauren5, lauren6, lauren7));
    }

    /*protected Class<?>[] getEntityViewClasses() {
        return new Class[]{
            WifeSimpleView.class,
            CatWithOwnerView.class,
            HusbandSimpleView.class
        };
    }*/
}
