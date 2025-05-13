import React from "react";
import { EventCard } from "./EventCard";

export const Events = () => {
  return (
    <div>
      <h1 className="py-5 text-xl font-semibold text-center mt-20">Events</h1>
      <div className="mt-5 px-5 flex flex-wrap gap-5">
        {[1, 1, 1].map((item) => (
          <EventCard />
        ))}
      </div>
    </div>
  );
};
