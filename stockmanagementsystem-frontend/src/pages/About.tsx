import React from 'react'
import { Link } from 'react-router-dom';

interface Props {};

const postId = 5;
export default class About extends React.Component<Props, any> {
    render(){
        return (
        <div>
            <div>
                about
            </div>
            <Link to={`/posts/${postId}`}>go to post {{postId}}</Link>
        </div>
    )}
};

export { About };