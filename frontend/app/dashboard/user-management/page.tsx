"use client"

import Navbar from "@/components/Navbar"
import { motion } from "framer-motion"
import { Button } from "@/components/ui/button"

export default function Home() {
    return (
        <div className="min-h-screen relative overflow-hidden bg-gradient-to-br from-[#0A2540] via-[#0F3D6B] to-[#0A2540]">
            <div className="absolute inset-0 bg-black/55 z-0" />

            <Navbar />

            <div className="relative z-10 flex flex-col items-center justify-center min-h-screen px-6 text-center pt-32 pb-32">
                <motion.h1
                    initial={{ opacity: 0, y: 80 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 1.4 }}
                    className="text-5xl md:text-7xl font-bold text-white mb-12 tracking-tight gradient-text"
                >
                    Villatel Orlando Resort
                </motion.h1>

                <motion.p
                    initial={{ opacity: 0, y: 60 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 1.4, delay: 0.3 }}
                    className="text-xl md:text-2xl text-white/90 mb-20 max-w-4xl leading-relaxed"
                >
                    Luxury Villas • Family Adventures • Orlando Magic
                </motion.p>

                <motion.div
                    initial={{ opacity: 0, y: 40 }}
                    animate={{ opacity: 1, y: 0 }}
                    transition={{ duration: 1.2, delay: 0.6 }}
                    className="flex flex-col sm:flex-row gap-12"
                >
                    <Button className="glass px-14 py-8 text-xl font-medium rounded-full border border-white/30 hover:border-white/60 transition-all shadow-2xl hover:shadow-[0_0_50px_rgba(30,218,255,0.5)]">
                        Book Now
                    </Button>
                    <Button variant="outline" className="glass px-14 py-8 text-xl font-medium rounded-full border border-white/30 hover:border-white/60 transition-all hover:shadow-[0_0_50px_rgba(0,250,154,0.5)]">
                        View Offers
                    </Button>
                </motion.div>
            </div>
        </div>
    )
}