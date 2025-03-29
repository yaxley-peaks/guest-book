import {useState} from "react";
import PropTypes from "prop-types";

AddTask.propTypes = {
    callbackFn: PropTypes.func,
}

export function AddTask(props) {
    const [taskName, setTaskName] = useState('');
    const [taskStatus, setTaskStatus] = useState(0);

    const handleTaskTitleChange = (e) => {
        if (e.target.value === '' || e.target.value === null) return;
        setTaskName(e.target.value);
    }
    const handleTaskStatusChange = (e) => {
        setTaskStatus(e.target.value);
    }

    const handleTaskAddition = () => {
        if (taskName === '' || taskName === null) return;
        props.callbackFn(taskName, taskStatus);
    }

    return <div className="text-center font-sans border-2 border-black dark:border-white rounded-2xl m-4 flex flex-col items-center">
        <p className="p-1 m-4 text-2xl">Add Task</p>
        <div className="p-1 m-4 flex-col flex justify-center items-center w-full text-xl">
            <span className="flex flex-row items-center justify-center flex-wrap">
            <label htmlFor="task-name" className="flex-none">Title: </label>
            <input type={"text"}
                   required={true}
                   id={"task-name"}
                   value={taskName}
                   placeholder="Title..."
                   onChange={handleTaskTitleChange}
                   className="p-2 m-4 flex-1 rounded"/>

            </span>
            <span className="flex flex-row items-center">
            <label htmlFor="task-status">Status: </label>
            <select className="p-2 m-4 rounded"
                    id={"task-status"}
                    value={taskStatus}
                    onChange={handleTaskStatusChange}>
                <option value={0}>Pending</option>
                <option value={1}>Done</option>
            </select>
            </span>
            <button className="p-4 m-4" onClick={() => {
                handleTaskAddition();
                setTaskName('');
            }}>Add Task
            </button>
        </div>
    </div>;

}