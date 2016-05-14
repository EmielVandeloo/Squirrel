package hillbillies.statement.action;

import hillbillies.expression.position.PositionExpression;
import hillbillies.part3.programs.SourceLocation;
import hillbillies.program.Program;
import hillbillies.world.Position;
import javafx.beans.binding.SetExpression;

public class Work extends Action {

	private PositionExpression expression;
	
	public Work(PositionExpression expression, SourceLocation sl) {
		super(sl);
		setExpression(expression);
	}
	
	public PositionExpression getExpression() {
		return this.expression;
	}
	
	public void setExpression(PositionExpression expression) {
		this.expression = expression;
	}

	@Override
	public void perform(Program program) {
		if (isToBeExecuted() && !program.hasStopped()) {
			if (program.hasTimeForStatement()) {
				program.decreaseTimerOneUnit();
				program.getUnit().workAt(getExpression().evaluate().x(), getExpression().
						evaluate().y(), getExpression().evaluate().z());
			}
		}
	}	

}
