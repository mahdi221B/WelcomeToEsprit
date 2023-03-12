package tn.esprit.spring.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class GeoPoint {
    Double latitude;
    Double longitude;
}

/*
While One-to-One relationships are also a way to model the relationship between entities, embedding is
 a different way of persisting the data.

In a One-to-One relationship, there are two separate entities with their own tables, and they are linked
 by a foreign key. On the other hand, embedding involves storing the fields of one entity as part of
 another entity in the same table.

Embedding is useful when you have a value object that is not used outside of the context of
the containing entity. For example, an Address object is often embedded in a Person entity, because the
address is not meaningful outside of the context of the person. By embedding the Address object in the
 Person entity, we can simplify the database schema and avoid creating unnecessary tables.
 */