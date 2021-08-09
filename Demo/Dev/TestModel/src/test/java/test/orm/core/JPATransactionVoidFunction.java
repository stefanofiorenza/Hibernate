package test.orm.core;

import java.util.function.Consumer;
import javax.persistence.EntityManager;


@FunctionalInterface
public interface JPATransactionVoidFunction extends Consumer<EntityManager> {

}
