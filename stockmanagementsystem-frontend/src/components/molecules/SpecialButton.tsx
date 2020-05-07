import React from 'react'
import { withRouter } from 'react-router-dom'

interface Props {}

// pake history, location, match dari parent component nya

export const SpecialButton = withRouter(({history}) => {
    return (
        <div>
            <button onClick={
                () => {history.push('/posts/special')}
            }>
                special page
            </button>
        </div>
    );
});
