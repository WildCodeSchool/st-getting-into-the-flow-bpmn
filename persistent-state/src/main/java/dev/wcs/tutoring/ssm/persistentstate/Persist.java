package dev.wcs.tutoring.ssm.persistentstate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler.PersistStateChangeListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

import java.util.List;

public class Persist {

    private final PersistStateMachineHandler handler;

    private final JdbcTemplate jdbcTemplate;

    private final PersistStateChangeListener listener = new LocalPersistStateChangeListener();

    public Persist(PersistStateMachineHandler handler, JdbcTemplate jdbcTemplate) {
        this.handler = handler;
        this.jdbcTemplate = jdbcTemplate;
        this.handler.addPersistStateChangeListener(listener);
    }

    public String listDbEntries() {
        List<Order> orders = jdbcTemplate.query(
                "select id, state from orders",
                (rs, rowNum) -> new Order(rs.getInt("id"), rs.getString("state")));
        StringBuilder buf = new StringBuilder();
        for (Order order : orders) {
            buf.append(order);
            buf.append("\n");
        }
        return buf.toString();
    }

    public void change(int order, String event) {
        Order o = jdbcTemplate.queryForObject("select id, state from orders where id = ?",
                (rs, rowNum) -> new Order(rs.getInt("id"), rs.getString("state")), new Object[] { order });

        handler.handleEventWithStateReactively(
                MessageBuilder.withPayload(event).setHeader("order", order).build(), o.state)
                .subscribe();
    }

    private class LocalPersistStateChangeListener implements PersistStateChangeListener {

        @Override
        public void onPersist(State<String, String> state, Message<String> message,
                              Transition<String, String> transition, StateMachine<String, String> stateMachine) {
            if (message != null && message.getHeaders().containsKey("order")) {
                Integer order = message.getHeaders().get("order", Integer.class);
                jdbcTemplate.update("update orders set state = ? where id = ?", state.getId(), order);
            }
        }
    }

}
