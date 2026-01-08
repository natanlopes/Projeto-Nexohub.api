create table consultas(

    id bigserial not null,
    medico_id bigint not null,
    paciente_id bigint not null,
    sala_id bigint not null,
    data timestamp not null,
    procedimento varchar(255),

    primary key(id),
    constraint fk_consultas_medico_id foreign key(medico_id) references profissionais(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id),
    constraint fk_consultas_sala_id foreign key(sala_id) references salas(id)

);