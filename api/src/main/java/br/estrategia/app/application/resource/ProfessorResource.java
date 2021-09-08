/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.estrategia.app.application.resource;

import br.estrategia.app.application.resource.suporte.AbstractRestBaseController;
import br.estrategia.app.domain.model.entidade.Professor;
import br.estrategia.app.domain.repository.ProfessorRepository;
import br.estrategia.app.infra.rest.URI_API_PATHS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = URI_API_PATHS.PROFESSORES_API)
public class ProfessorResource extends AbstractRestBaseController<Professor, Long> {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public CrudRepository<Professor, Long> getRepositorio() {
        return professorRepository;
    }
}
