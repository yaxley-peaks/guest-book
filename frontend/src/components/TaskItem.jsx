import PropTypes from "prop-types";
import {useState} from "react";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faPenToSquare, faSave} from "@fortawesome/free-solid-svg-icons";
import {faTrashCan} from "@fortawesome/free-regular-svg-icons";

export function TaskItem(props) {
    const [status, setStatus] = useState(props.task.completed);
    const [taskTitle, setTaskTitle] = useState(props.task.title);
    const [isEditing, setEditing] = useState(false);
    const wrapItemInStrikeThrough = (text, status) => {
        if (status) {
            return <s>{text}</s>;
        } else {
            return text;
        }
    }
    let renderedJsx = null;

    switch (isEditing) {
        case true:
            renderedJsx = (<>
                <li className="p-1 m-1 text-xl flex items-center" key={props.task.id}>
                    <input type="text" className="max-w-full w-full m-4 p-4" value={taskTitle} onChange={(e) => {
                        setTaskTitle(e.target.value);
                        props.onEdit(props.task.id, e.target.value)
                    }}/>
                    <button title="Save" className="m-1 ml-auto p-1" onClick={() => setEditing(false)}>
                        <FontAwesomeIcon icon={faSave}/>
                    </button>
                </li>
                <hr className="border border-gray-800 dark:border-white"/>
            </>);
            break;
        case false:
            renderedJsx = (<>
                <li className="p-1 m-1 text-xl flex items-center text-justify" key={props.task.id}>
                    <input type={"checkbox"}
                           onChange={(e) => {
                               setStatus(e.target.checked);
                               props.onDone(props.task.id, e.target.checked)
                           }}
                           className="m-3 scale-150"
                           checked={status}/>
                    {wrapItemInStrikeThrough(taskTitle, status)}
                    <span className="m-1 pl-2 whitespace-nowrap ml-auto">
                    <button className="m-1 p-1" title="Edit" onClick={() => setEditing(true)}>
                        
                        <FontAwesomeIcon icon={faPenToSquare}/>
                    </button>
                    <button className="m-1 p-1" title="Delete" onClick={() => props.onDelete(props.task.id)}>
                        <FontAwesomeIcon icon={faTrashCan} className="text-red-700"/>
                    </button>
                </span>
                </li>
                <hr className="border border-gray-800 dark:border-white"/>
            </>);
            break;
        default:
            break;
    }


    return renderedJsx;
}

TaskItem.propTypes = {
    onChange: PropTypes.func,
    task: PropTypes.any,
    tasks: PropTypes.arrayOf(PropTypes.any),
    onEdit: PropTypes.func,
    onDelete: PropTypes.func,
    onDone: PropTypes.func
};
