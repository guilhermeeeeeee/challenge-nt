CREATE TABLE public.status (
  id INT NOT NULL,
  name VARCHAR(45) NULL,
  PRIMARY KEY (id));


CREATE TABLE public.subscription (
  id VARCHAR(100) NOT NULL,
  status_id INT NOT NULL,
  created_at TIMESTAMP NULL,
  updated_at TIMESTAMP NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (status_id)
    REFERENCES public.status (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE public.event_history (
  id INT NOT NULL,
  type VARCHAR(200) NOT NULL,
  subscription_id VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NULL,
  PRIMARY KEY (id),
    FOREIGN KEY (subscription_id)
    REFERENCES public.subscription (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE SEQUENCE hibernate_sequence START 1;


