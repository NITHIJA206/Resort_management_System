"use client"

import Link from "next/link"
import { usePathname } from "next/navigation"
import { motion } from "framer-motion"
import { Button } from "@/components/ui/button"
import { Menu } from "lucide-react"
import { Sheet, SheetContent, SheetTrigger } from "@/components/ui/sheet"

const navLinks = [
    { name: "Dashboard", href: "/dashboard" },
    { name: "Rooms", href: "/dashboard/room-management" },
    { name: "Bookings", href: "/dashboard/booking-system" },
    { name: "Payments", href: "/dashboard/payment-services" },
    { name: "Users", href: "/dashboard/user-management" },
]

export default function Navbar() {
    const pathname = usePathname()

    const NavContent = () => (
        <div className="flex items-center gap-10">
            {navLinks.map((link) => (
                <Link
                    key={link.name}
                    href={link.href}
                    className={`text-base font-medium transition-all duration-300 ${
                        pathname === link.href
                            ? "text-white scale-105"
                            : "text-white/80 hover:text-white hover:scale-105"
                    }`}
                >
                    {link.name}
                </Link>
            ))}
        </div>
    )

    return (
        <motion.nav
            initial={{ y: -100 }}
            animate={{ y: 0 }}
            transition={{ duration: 0.7, ease: "easeOut" }}
            className="fixed top-0 left-0 right-0 z-50 backdrop-blur-2xl border-b border-white/10 glass px-6 md:px-12 py-5 shadow-lg"
        >
            <div className="max-w-7xl mx-auto flex items-center justify-between">
                {/* Logo */}
                <Link href="/" className="text-3xl font-extrabold gradient-text tracking-tight hover:scale-105 transition-transform">
                    ResortPro
                </Link>

                {/* Desktop Nav */}
                <div className="hidden md:flex items-center gap-14">
                    <NavContent />
                </div>

                {/* Right side: Login / Logout */}
                <div className="flex items-center gap-6">
                    <Button className="glass px-8 py-3 text-base font-medium rounded-full border border-white/20 hover:border-white/50 transition-all shadow-xl hover:shadow-[0_0_30px_rgba(255,255,255,0.3)]">
                        Login
                    </Button>

                    {/* Mobile Menu */}
                    <Sheet>
                        <SheetTrigger asChild className="md:hidden">
                            <Button variant="ghost" size="icon" className="text-white">
                                <Menu className="h-7 w-7" />
                            </Button>
                        </SheetTrigger>
                        <SheetContent side="right" className="w-80 glass border-l border-white/10">
                            <div className="flex flex-col gap-8 mt-16">
                                <NavContent />
                                <Button className="glass w-full py-6 text-lg font-medium">
                                    Login
                                </Button>
                            </div>
                        </SheetContent>
                    </Sheet>
                </div>
            </div>
        </motion.nav>
    )
}