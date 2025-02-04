-- DROP TABLE IF EXISTS public.task;

CREATE TABLE IF NOT EXISTS public.task
(
    id bigint NOT NULL DEFAULT nextval('task_id_seq'::regclass),
    x_request_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    execute_at timestamp without time zone NOT NULL,
    type character varying(100) COLLATE pg_catalog."default" NOT NULL,
    body text COLLATE pg_catalog."default",
    service_name character varying(255) COLLATE pg_catalog."default",
    target_topic character varying(255) COLLATE pg_catalog."default",
    status character varying(50) COLLATE pg_catalog."default",
    result_message text COLLATE pg_catalog."default",
    execution_time_in_millis bigint,
    name character varying(255) COLLATE pg_catalog."default",
    execution_type character varying(50) COLLATE pg_catalog."default",
    lgi_customer_id character varying(255) COLLATE pg_catalog."default",
    country character varying(100) COLLATE pg_catalog."default",
    created_at timestamp without time zone DEFAULT now(),
    feedback_required boolean,
    CONSTRAINT task_pkey PRIMARY KEY (id)
)