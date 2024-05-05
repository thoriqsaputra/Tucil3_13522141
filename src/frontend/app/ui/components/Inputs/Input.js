'use client';

import { Input } from "@material-tailwind/react";
import { useState} from "react";

export default function TextInput({label, handleChange, value, placeholder}) {
    const [isValid, setIsValid] = useState(true);

    const handleChangeAndCheckWord = async (e) => {
        if (handleChange) {
            handleChange(e);
        }
        if (e.target.value === '') {
            setIsValid(true);
            return;
        }
        await checkWord(e);
    } 

    const checkWord = async (e) => {
        const word = e.target.value;
        const response = await fetch('http://localhost:8080/validword', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(word),
        });
        
        // Assuming response.json() returns a boolean indicating validity
        const isValidWord = await response.json();
        console.log(isValidWord.valid);
        // Update the isValid state with the validity of the word
        setIsValid(isValidWord.valid);
        console.log(isValid);
    } 

    return (
        <div className="relative flex flex-col justify-center">
            {isValid ? null :
            <div className="text-sm font-medium text-red-500 mb-1">
                {label} must be a valid english word!
            </div>
            }
            <Input
                variant="outline"
                label={label}
                placeholder={placeholder}
                value={value}
                color="white"
                onChange={handleChangeAndCheckWord} // Combine handleChange and checkWord
            />
        </div>
    );
}
