import { Card, CardContent, CardMedia, IconButton, Typography } from '@mui/material'
import React from 'react'
import DeleteIcon from '@mui/icons-material/Delete';
import { CardActions } from '@mui/material';


export const EventCard = () => {
  return (
    <div>
        <Card sx={{width: 345}}>
            <CardMedia 
            sx={{ height: 345 }}
            image='https://images.pexels.com/photos/699953/pexels-photo-699953.jpeg?auto=compress&cs=tinysrgb&w=600'/>
            <CardContent> 
                <Typography variant='h5'>
                    Indian Fast Food
                </Typography>
                <Typography variant='body2'>
                    50% off on your first order
                </Typography>
                <div className='py-2 space-y-2'>
                    <p>{"mumbai"}</p>
                    <p className="text-sm text-blue-500">May 14, 2025 12:00 AM </p>
                    <p className="text-sm text-red-500">May 15, 2025 12:00 AM </p>
                </div>
            </CardContent>
        </Card>
        {false && <CardActions>
            <IconButton>
                <DeleteIcon />
            </IconButton>
        </CardActions>}
    </div>
  )
}
