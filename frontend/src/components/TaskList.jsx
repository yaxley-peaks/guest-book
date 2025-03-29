import PropTypes from "prop-types";
import {useState} from "react";
import {TaskItem} from "./TaskItem.jsx";


export function TaskList(props) {
    const [, setChecked] = useState(true);
    const handleTaskMarkAsRead = (e) => {
        setChecked(!e.target.checked);
    }
    return <ol className="text-left">
        {props.tasks.map((task) =>
            (<TaskItem key={task.id}
                       onChange={handleTaskMarkAsRead} task={task}
                       onEdit={props.taskEditDispatcher}
                       onDelete={props.taskDeleteDispatcher}
                       onDone={props.taskDoneDispatcher}
            />))}
    </ol>;
}

TaskList.propTypes = {
    tasks: PropTypes.arrayOf(PropTypes.any),
    taskEditDispatcher: PropTypes.func,
    taskDeleteDispatcher: PropTypes.func,
    taskDoneDispatcher: PropTypes.func,
};
